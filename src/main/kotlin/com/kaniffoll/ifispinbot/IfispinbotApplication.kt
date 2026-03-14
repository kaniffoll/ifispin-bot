package com.kaniffoll.ifispinbot

import com.kaniffoll.ifispinbot.config.TelegramConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class IfispinbotApplication

fun main(args: Array<String>) {
	runApplication<IfispinbotApplication>(*args)
}
