define([
    'jquery',
    'underscore',
    'backbone',
    '../models/place'
], function($, _, Backbone, Place) {
    var Places = Backbone.Collection.extend({
        model: Place,
        url: function() {
            return 'api/places';
        }
    });

    return Places;
});
