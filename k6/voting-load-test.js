import http from 'k6/http';
import { check, sleep } from 'k6';

const BASE_URL = 'http://voting-api:8080/api/v1';

export const options = {
    vus: 1000,          // 100 usuários simultâneos
    duration: '10s',   // duração do teste
};

export function setup() {

    // 1️⃣ Criar agenda
    const agendaPayload = JSON.stringify({
        title: "Performance Test Agenda",
        description: "Teste"
    });

    const agendaRes = http.post(
        `${BASE_URL}/agendas`,
        agendaPayload,
        { headers: { 'Content-Type': 'application/json' } }
    );

    check(agendaRes, {
        'agenda created': (r) => r.status === 201,
    });

    const agendaId = agendaRes.json('id');

    // 2️⃣ Abrir sessão de votação
    const endTime = new Date(Date.now() + 5 * 60 * 1000)
        .toISOString()
        .slice(0, 19);

    const sessionPayload = JSON.stringify({
        agenda_id: agendaId,
        end_time: endTime
    });

    const sessionRes = http.post(
        `${BASE_URL}/voting-sessions`,
        sessionPayload,
        { headers: { 'Content-Type': 'application/json' } }
    );

    check(sessionRes, {
        'session created': (r) => r.status === 201,
    });

    const votingSessionId = sessionRes.json('id');

    return { votingSessionId };
}

export default function (data) {

    const associateId = generateValidCpf();

    const payload = JSON.stringify({
        voting_session_id: data.votingSessionId,
        associate_id: associateId,
        vote_type: Math.random() > 0.5 ? "Sim" : "Não"
    });

    const res = http.post(
        `${BASE_URL}/votes`,
        payload,
        { headers: { 'Content-Type': 'application/json' } }
    );

    check(res, {
        'vote accepted': (r) => r.status === 200,
    });

    sleep(0.2);
}

function generateValidCpf() {

    let cpf = [];

    // 9 primeiros dígitos
    for (let i = 0; i < 9; i++) {
        cpf[i] = Math.floor(Math.random() * 10);
    }

    // cálculo do primeiro dígito verificador
    let sum = 0;
    for (let i = 0; i < 9; i++) {
        sum += cpf[i] * (10 - i);
    }

    let firstDigit = (sum * 10) % 11;
    if (firstDigit === 10) firstDigit = 0;

    cpf[9] = firstDigit;

    // cálculo do segundo dígito verificador
    sum = 0;
    for (let i = 0; i < 10; i++) {
        sum += cpf[i] * (11 - i);
    }

    let secondDigit = (sum * 10) % 11;
    if (secondDigit === 10) secondDigit = 0;

    cpf[10] = secondDigit;

    return cpf.join('');
}