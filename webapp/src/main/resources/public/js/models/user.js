define([
    'jquery',
    'underscore',
    'backbone'
], function($, _, Backbone) {
    var User = Backbone.Model.extend({
        defaults: {
            'username': ''
        }
    });

    return User;
});
