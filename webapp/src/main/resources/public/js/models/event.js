define([
    'jquery',
    'underscore',
    'backbone',
    'models/place',
    'models/user',
    'collections/users'
], function($, _, Backbone, Place, User, Users) {
    var Event = Backbone.Model.extend({
        defaults: {
            'name': '',
            'dateTime': '',
            'hashtag': '',
            'location': new Place(),
            'categories': [],
            'tickets': false,
            'observators': new Users()
        },
        
        urlRoot:  '/api/events',
        watchUrl: function() {
            return '/api/events/' + this.get('id') + '/watch'
        },

        parse: function(data) {
            data.location = new Place(data.location);

            var observators = new Users();
            _.forEach(data.observators, function(observator) {
                observators.add(new User({username: observator.username}));
            }.bind(this));
            data.observators = observators;

            return data;
        }
        
    });

    return Event;
});
