define([
    'jquery',
    'underscore',
    'backbone',
    '../models/event'
], function($, _, Backbone, Event) {
    var Events = Backbone.Collection.extend({
        model: Event,
        url: function() {
            return 'api/events';
        }
    });

    return Events;
});
