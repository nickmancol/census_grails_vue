package com.dataiku.hr.census


import grails.dev.commands.*

class CensusCommand implements GrailsApplicationCommand, grails.validation.Validateable {
    def censusService
    String field

    static constraints = {
        field validator: { val, obj ->
            obj.censusService.canQuery(obj.field)
        }
    }

    boolean handle() {
        return false
    }
}
