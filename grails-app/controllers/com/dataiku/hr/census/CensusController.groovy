package com.dataiku.hr.census

class CensusController {
	static responseFormats = ['json', 'xml']
	def censusService

	def index() {
		[options: censusService.colMapping.sort()]
	}

	def show(String id) {
		[results: censusService.query(id)]
	}
}
