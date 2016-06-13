define([
    'jquery',
    'underscore',
    'backbone',
    'models/place',
    'models/user',
    'models/event'
], function($, _, Backbone, Place, User, Event) {
    var Post = Backbone.Model.extend({
        defaults: {
            'id': 0,
            'event': new Event(),
            'publishDate': '',
            'comments': null
        },

        url: function() {
            return '/api/posts/' + this.get('id');
        },

        parse: function(data) {
            data.event = new Event(Event.prototype.parse(data.event));
            // _.forEach(data.comments, function(comment) {
            //     this.get('comments').add(new Comment(comment));
            // }.bind(this));
            // data.comments = this.get('comments');

            return data;
        }
    });

    return Post;
});
