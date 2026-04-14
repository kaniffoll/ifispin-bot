package com.kaniffoll.ifispinbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class IfispinbotApplication
fun main(args: Array<String>) {
	runApplication<IfispinbotApplication>(*args)
}
