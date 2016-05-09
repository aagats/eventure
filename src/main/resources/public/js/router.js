define([
    'jquery',
    'underscore',
    'backbone',
    'views/main-page'
], function ($, _, Backbone, MainPageView) {
    var AppRouter = Backbone.Router.extend({
        routes: {
            '!/events/:id': 'showEvent',
            '!': 'mainPage'
        },

        mainPage: function () {
            var mainPageView = new MainPageView({
                el: $('.main-container')
            });
            mainPageView.render();
        },

        showEvent: function(id) {
            
            
        }

    });

    return AppRouter;
})
;