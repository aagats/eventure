define([
    'jquery',
    'underscore',
    'backbone',
    '../models/post'
], function($, _, Backbone, Post) {
    var Watches = Backbone.Collection.extend({
        model: Post,
        url: function() {
            return 'api/watches';
        }
    });

    return Watches;
});
