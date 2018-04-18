package com.dataiku.hr.census

import groovy.sql.Sql

class CensusService {
    def dataSource

    def colMapping = [
      "class of worker"
      ,"industry code"
      ,"occupation code"
      ,"education"
      ,"wage per hour"
      ,"last education"
      ,"marital status"
      ,"major industry code"
      ,"major occupation code"
      ,"mace"
      ,"hispanice"
      ,"sex"
      ,"member of labor"
      ,"reason for unemployment"
      ,"fulltime"
      ,"capital gain"
      ,"capital loss"
      ,"dividends"
      ,"income tax liability"
      ,"previous residence region"
      ,"previous residence state"
      ,"household-with-family"
      ,"household-simple"
      ,"weight"
      ,"msa-change"
      ,"reg-change"
      ,"within-reg-change"
      ,"lived-here"
      ,"migration prev res in sunbelt"
      ,"num persons worked for employer"
      ,"family members under 118"
      ,"father birth country"
      ,"mother birth country"
      ,"birth country"
      ,"citizenship"
      ,"own business or self employed"
      ,"fill questionnaire for veteran's admin"
      ,"veterans benefits"
      ,"weeks worked in year"
      ,"year"
      ,"salary range"
    ]

    private Sql sql() {
        new Sql(dataSource)
    }

    def canQuery(String field) {
      return colMapping.contains(field.toLowerCase())
    }

    def query(String field, int limit = 100){
        def res = []
        def total = 1
        //TODO: horrible way to validate, fix it
        if( !canQuery( field ) ){
          field = "Column not available, using global count"
        } else {
          total = countRows(field)
        }

        def queryStr = """select "${field}" value, count(*) num, avg(age) average
          from census_learn_sql
          group by "${field}"
          order by num desc"""
        Sql sql = sql()
        //TODO: change to add pagination
        sql.eachRow(queryStr, 0, limit) {
          res.add(['value':it.value?:'n/a', 'num':it.num, 'average':it.average?:'n/a'])
        }

        return [results:res, limit:limit, total:total]
    }

    def countRows(String field){
        def queryStr = """select count(*) total from (select distinct("${field}") from census_learn_sql)"""
        Sql sql = sql()
        sql.rows(queryStr).first().total
    }
}
