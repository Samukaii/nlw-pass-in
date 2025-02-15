package rocketseat.com.passin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.dto.attendee.AttendeeBadgeResponseDTO;
import rocketseat.com.passin.services.AttendeeService;
import rocketseat.com.passin.services.CheckInService;

import java.net.URI;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping("{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeResponseDTO> getTest(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        AttendeeBadgeResponseDTO responseDTO = attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/{attendeeId}/check-in")
    public ResponseEntity<String> registerCheckIn(@PathVariable String attendeeId,  UriComponentsBuilder uriComponentsBuilder) {
        attendeeService.checkInAttendee(attendeeId);

        URI badgeUrl = uriComponentsBuilder.path("/attendees/{attendeeId}/badge")
                .buildAndExpand(attendeeId).toUri();

        return ResponseEntity.created(badgeUrl).build();
    }
}
