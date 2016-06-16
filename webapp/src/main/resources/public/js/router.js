define([
    'jquery',
    'underscore',
    'backbone',
    'views/main-page',
    'views/watches-page',
    'views/post',
    'models/post'
], function ($, _, Backbone, MainPageView, WatchesPageView, PostView, Post) {
    var AppRouter = Backbone.Router.extend({
        routes: {
            'posts/:id': 'showPost',
            'watches': 'watchesPage',
            '': 'mainPage'
        },

        mainPage: function () {
            var mainPageView = new MainPageView({
                el: $('.main-container')
            });
            mainPageView.render();
        },
        
        watchesPage: function() {
            var watchesPageView = new WatchesPageView({
                el: $('.main-container')
            });
            watchesPageView.render();
        },
        
        showPost: function(id) {
            var model = new Post({id: id});
            model.fetch()
                .done(function() {
                    var postView = new PostView({
                        el: $('.main-container'),
                        model: model
                    });
                    $('.main-container').html('');
                    postView.render();
                });
        }

    });

    return AppRouter;
})
;