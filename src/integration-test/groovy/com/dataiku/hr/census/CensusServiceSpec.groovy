package com.dataiku.hr.census

import grails.testing.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification
import groovy.sql.Sql

@Integration
class CensusServiceSpec extends Specification implements ServiceUnitTest<CensusService>{

    def dataSource

    def setup() {
        service.dataSource = dataSource
    }

    def cleanup() {
    }

    void "test validation"() {
        expect:"Valid query field"
            service.canQuery('No') == false
            service.canQuery('own business or self employed') == true
    }

    void "test query"() {
        given:
          def results = service.query('own business or self employed')['results'].first()
        expect:"Valid query field"
            results['value'] == '0'
            results['num'] == 3
            results['average'] == 56.333333333333336
    }
}
