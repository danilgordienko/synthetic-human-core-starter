package ru.danilgordienko.synthetic_human_core_starter.CommandModule;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Command {

    @Size(max = 1000, message = "Command content must be at most 1000 characters")
    String description;

    CommandPriority priority;

    @Size(max = 100, message = "Author must be at most 100 characters")
    String author;

    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "Time must be in HH:mm format (24-hour)")
    String time;
}

