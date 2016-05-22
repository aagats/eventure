define([
    'jquery',
    'underscore',
    'backbone',
    'router',
    'bootstrap'
], function ($, _, Backbone, AppRouter, Bootstrap) {
    var App = {
        initialize: function () {
            var router = new AppRouter();
            Backbone.history.start({pushState: true, root: '/'});
            $(document).on("click", "a", function(e)
            {
                var href = $(e.currentTarget).attr('href');

                if (href !== 'login') {
                    e.preventDefault();

                    router.navigate(href, true);
                }
            });
        }
    };

    return App;
});