//on document ready
$(createTooltips);

function createTooltips() {
    var container = "<div class=\"tooltip\"></div>";
    var deleteInfo = "<span class=\"tooltiptext\">Delete</span>";
    var updateInfo = "<span class=\"tooltiptext\">Update</span>";
    var moreInfo = "<span class=\"tooltiptext\">More information</span>";
    var changeTimeInfo = "<span class=\"tooltiptext\">Change Estimated Time</span>";
    var availableInfo = "<span class=\"tooltiptext\">Set to Available</span>";
    var unavailableInfo = "<span class=\"tooltiptext\">Set to Unavailable</span>";

    // Delete button
    $(".delete").wrap(container);
    $(".delete").parent().append(deleteInfo);

    // Update button
    $(".update").wrap(container);
    $(".update").parent().append(updateInfo);

    // More information button
    $(".check").wrap(container);
    $(".check").parent().append(moreInfo);

    //Change Estimated Time button
    $(".chTime").wrap(container);
    $(".chTime").parent().append(changeTimeInfo);

    //Set product available button
    $(".available").wrap(container);
    $(".available").parent().append(availableInfo);

    //Set product unavailable button
    $(".unavailable").wrap(container);
    $(".unavailable").parent().append(unavailableInfo);
}
