package com.dataiku.hr.census


import grails.rest.*
import grails.converters.*

class SpaController {
	static responseFormats = ['json', 'xml']

    def index() {
			render(view:"index")
		}
}
