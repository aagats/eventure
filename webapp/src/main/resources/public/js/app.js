define([
    'jquery',
    'underscore',
    'backbone',
    'router'
], function ($, _, Backbone, AppRouter) {
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