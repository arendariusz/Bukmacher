package com.example.bukmacher.config;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

public interface EventValidationGroups {
    interface Create {}
    interface Update {}

    @GroupSequence({ Default.class, Create.class })
    interface CreateSequence {}

    @GroupSequence({ Default.class, Update.class })
    interface UpdateSequence {}
}
