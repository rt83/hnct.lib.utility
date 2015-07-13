package hnct.lib.utility

import org.slf4j.LoggerFactory
import org.slf4j.Logger

trait Logable {
	
	self =>
		
	final val log : Logger = LoggerFactory.getLogger(self.getClass.getName)
	
}