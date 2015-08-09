(function () {
    'use strict';

    angular
        .module('app')
        .config(['$stateProvider', animationsConfig])
	.animation('.view-fade-in', viewFadeIn)
	.animation('.moving-sideways', movingSideways)
	.animation('.show-hide', showHide)
        .controller('animationsController', ['$scope',animationsController]);


	function showHide(){
		return {
			beforeAddClass: function (element, className, done) {
console.log('beforeAddClass=' + className);
				if (className === 'ng-hide') {
					console.log('beforeAddClass');
					element.animate({
						opacity: 0
					}, 800, done);
				} else {
					done();
				}
			},
			removeClass: function (element, className, done) {
console.log('removeClass=' + className);
				if (className === 'ng-hide') {
					console.log('removeClass');
					element.css('opacity', 0);
					element.animate({
						opacity: 1
					}, 800, done);
				} else {
					done();
				}
			}
		};
	}

	function movingSideways(){
		return {
			enter: function (element, done) {
				var width = element.width();
				element.css({
					position: 'relative',
					right: -100,
					opacity: 0
				});
				element.animate({
					right: 0,
					opacity: 1
				}, done);
			},
			leave: function (element, done) {
				element.css({
					position: 'relative',
					right: 0,
					opacity: 1
				});
				element.animate({
					right: -100,
					opacity: 0
				}, done);
			},
			move: function (element, done) {
				element.css({
					right: "100px",
					opacity: 0
				});
				element.animate({
					right: "0px",
					opacity: 1
				}, done);
			}
		};
	}

	function viewFadeIn(){
		return {
			enter: function(element, done) {
				element.css({
					opacity: 0,
					position: "relative",
					right: "100px"
				})
				.animate({
					top: 0,
					right: 0,
					opacity: 1
				}, 2000, done);
			}
		};
	}

    function animationsConfig($stateProvider) {
        $stateProvider
	.state('animations', {
        	url: '/animations',
        	views: {
            		// the main template will be placed here (relatively named)
            		'': { 
				templateUrl: 'app/modules/animations/animations.html',
				controller: 'animationsController'
			}
		}
        })

    }

    function animationsController($scope) {
		$scope.grades = ['Niedostateczny', 'Dopuszczający', 'Dostateczny','Dobry', 'Bardzo dobry','Celujący'];
    }

}());
