plugins {
	java
	id("org.springframework.boot") version "4.0.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.sonarqube") version "7.2.3.7755"
	id("jacoco")
}

jacoco {
	toolVersion = "0.8.10"
}

group = "Sicredi"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.2")
	implementation ("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.springframework.boot:spring-boot-starter-flyway")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	runtimeOnly("org.postgresql:postgresql")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:testcontainers-junit-jupiter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.testcontainers:junit-jupiter:1.19.1")
	testImplementation("org.testcontainers:postgresql:1.21.4")}

tasks.test {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		xml.required.set(true)
		html.required.set(true)
		csv.required.set(false)
	}
	classDirectories.setFrom(fileTree("build/classes/java/main") {
		exclude(
			"Sicredi/Teste/presentation/controller/v1/exceptions/**",
			"Sicredi/Teste/TesteApplication.class"
		)
	})
	sourceDirectories.setFrom(files("src/main/java"))
	executionData.setFrom(files("build/jacoco/test.exec"))
}

tasks.register("printEnv") {
	doLast {
		println("SONAR_KEY = ${System.getenv("SONAR_KEY")}")
	}
}

sonarqube {
	properties {
		property("sonar.projectKey", "jefersonbitencourtv_sicredi_teste")
		property("sonar.organization", "jefersonbitencourtv")
		property("sonar.host.url", "https://sonarcloud.io")
		property("sonar.login", System.getenv("SONAR_KEY"))
		property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
		property("sonar.coverage.exclusions",
			"Sicredi.Teste.presentation.controller.v1.exceptions.**,Sicredi.Teste.TesteApplication")
		property("sonar.exclusions",
			"Sicredi.Teste.presentation.controller.v1.exceptions.**,Sicredi.Teste.TesteApplication")
	}
}