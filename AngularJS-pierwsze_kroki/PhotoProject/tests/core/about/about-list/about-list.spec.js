describe('aboutListCtrl', function(){
	var scope; // scope, z którego będziemy korzystać podczas testów
	
	// mock app umożliwiający wstrzyknięcie zależności
	beforeEach(angular.mock.module('photoApp'));

	// mock kontrolera umożliwiający wstrzykiwanie zależności oraz zawarcie $rootScope i $controller
	beforeEach(angular.mock.inject(function($rootScope, $controller){
		// tworzymy pusty scope
		scope = $rootScope.$new();
		// deklarujemy kontroler i wstrzykujemy pusty scope
		$controller('photoApp.core.about.aboutListCtrl', { $scope: scope });
	}));

	// startujemy z testami
	it('powinien zawierać wartość', function(){
		expect(scope.message).toBe("test");
	});
		
	it('tablica powinna zawierać wartość', function () {
		expect(scope.scotches).toContain(jasmine.objectContaining({name: 'Macallan 12' }));
	});

	it('tablica nie powinna zawierać wartości', function () {
		expect(scope.scotches).not.toContain(jasmine.objectContaining({name: 'Macallan 123' }));
	});
});
