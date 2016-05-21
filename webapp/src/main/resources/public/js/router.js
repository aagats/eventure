define([
    'jquery',
    'underscore',
    'backbone',
    'views/main-page',
    'views/login-page'
], function ($, _, Backbone, MainPageView, LoginPageView) {
    var AppRouter = Backbone.Router.extend({
        routes: {
            '/events/:id': 'showEvent',
            '/dashboard': 'mainPage',
            '': 'loginPage'
        },

        mainPage: function () {
            var mainPageView = new MainPageView({
                el: $('.main-container')
            });
            mainPageView.render();
        },
        
        loginPage: function() {
            var loginPageView = new LoginPageView({
                el: $('.main-container')
            });
            loginPageView.render();
        },

        showEvent: function(id) {
            
            
        }

    });

    return AppRouter;
})
;