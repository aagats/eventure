define([
    'jquery',
    'underscore',
    'backbone'
], function($, _, Backbone) {
    var Place = Backbone.Model.extend({
        defaults: {
            'id': 0,
            'name': '',
            'city': '',
            'street': '',
            'buildingNumber': 0
        }
    });

    return Place;
});
