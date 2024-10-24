package uz.ilmnajot.organisation_task.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InfoOfRate {

    private Double rate;
    private String pinfl;
}
