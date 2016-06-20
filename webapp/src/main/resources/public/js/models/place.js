define([
    'jquery',
    'underscore',
    'backbone'
], function($, _, Backbone) {
    var Place = Backbone.Model.extend({
        defaults: {
            'name': '',
            'city': '',
            'street': '',
            'buildingNumber': 0
        },

        urlRoot: '/api/places'
    });

    return Place;
});
