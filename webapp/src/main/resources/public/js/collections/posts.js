define([
    'jquery',
    'underscore',
    'backbone',
    '../models/post'
], function($, _, Backbone, Post) {
    var Posts = Backbone.Collection.extend({
        model: Post,
        url: function() {
            return 'api/posts';
        }
    });

    return Posts;
});
