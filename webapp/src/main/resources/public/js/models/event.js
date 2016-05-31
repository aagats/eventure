define([
    'jquery',
    'underscore',
    'backbone',
    'models/place',
    'collections/users'
], function($, _, Backbone, Place, Users) {
    var Event = Backbone.Model.extend({
        defaults: {
            'id': 0,
            'name': '',
            'date': '',
            'hashtag': '',
            'location': new Place(),
            'categories': [],
            'tickets': false,
            'observators': new Users()
        },
        
        url: function() {
            return '/api/events/' + this.get('id');
        },

        parse: function(data) {
            data.location = new Place(data.location);

            return data;
        }
    });

    return Event;
});
