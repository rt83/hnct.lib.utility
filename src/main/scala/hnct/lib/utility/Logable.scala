package hnct.lib.utility

import org.slf4j.LoggerFactory

trait Logable {
	
	self =>
		
	final val log = LoggerFactory.getLogger(self.getClass.getName)
	
}