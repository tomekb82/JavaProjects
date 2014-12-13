package org.grails.samples

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Owner)
class OwnerSpec extends Specification implements DomainDataFactory {

	Owner owner = validOwner()
	
	def setup() {
		mockForConstraintsTests Owner
	}
	
	def 'a valid Owner has no errors'() {
		when:
		owner.validate()
		
		then:
		!owner.hasErrors()
		!owner.pets
	}
	
	@Unroll
	def 'Owner address with value #value validation error is #error'() {
		given:
		owner.address = value
		owner.validate()
		
		expect:
		owner.errors.address == error
		
		where:
		value        || error
		''           || 'blank'
		'123 EZ St.' || null
	}
	
	@Unroll
	def 'city with value #value validation error is #error'() {
		given:
		owner.city = value
		owner.validate()
		
		expect:
		owner.errors.city == error
		
		where:
		value     || error
		null      || 'nullable'
		' '       || 'blank'
		'Madison' || null
	}

	@Unroll
	def 'telephone with value #value validation error is #error'() {
		given:
		owner.telephone = value
		owner.validate()
		
		expect:
		owner.errors.telephone == error
		
		where:
		value     || error
		null      || 'nullable'
		''        || 'blank'
		'a123456' || 'matches'
		'1234567' || null
	}
}
