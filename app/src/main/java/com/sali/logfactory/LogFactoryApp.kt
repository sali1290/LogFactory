package com.sali.logfactory

import android.app.Application
import com.sali.logfactory.factory.LogFactory
import com.sali.logfactory.formatter.DefaultLogMessageFormatter
import com.sali.logfactory.logger.EmailLogger
import com.sali.logfactory.logger.FileLogger
import com.sali.logfactory.logger.LogcatLogger
import com.sali.logfactory.models.EmailLoggerConfig
import com.sali.logfactory.models.FileLoggerConfig
import com.sali.logfactory.models.ThresholdType

class LogFactoryApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val emailLogger = EmailLogger(
            config = EmailLoggerConfig(
                thresholdType = ThresholdType.Counter,
                logCountThreshold = 3,
                senderEmail = "sender@gmail.com",
                senderPassword = "sender password",
                recipientEmail = "recepient@gmail.com",
            )
        )

        val fileLogger = FileLogger(config = FileLoggerConfig())
        val logcatLogger = LogcatLogger(formatter = DefaultLogMessageFormatter)

        LogFactory.configureLoggers(
            context = this,
            enabledLoggers = arrayOf(fileLogger, emailLogger, logcatLogger)
        )
    }

}