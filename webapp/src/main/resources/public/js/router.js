define([
    'jquery',
    'underscore',
    'backbone',
    'views/main-page',
    'views/post',
    'models/post'
], function ($, _, Backbone, MainPageView, PostView, Post) {
    var AppRouter = Backbone.Router.extend({
        routes: {
            'posts/:id': 'showPost',
            '': 'mainPage'
        },

        mainPage: function () {
            var mainPageView = new MainPageView({
                el: $('.main-container')
            });
            mainPageView.render();
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