define([
    'jquery',
    'underscore',
    'backbone',
    'views/main-page',
    'views/login-page',
    'views/event'
], function ($, _, Backbone, MainPageView, LoginPageView, EventView) {
    var AppRouter = Backbone.Router.extend({
        routes: {
            'events/:id': 'showEvent',
            '': 'mainPage'
        },

        mainPage: function () {
            var mainPageView = new MainPageView({
                el: $('.main-container')
            });
            mainPageView.render();
        },

        showEvent: function(id) {
            var eventPageView = new EventView({
                el: $('.main-container'),
                id: id
            });
            eventPageView.render();
            
        }

    });

    return AppRouter;
})
;