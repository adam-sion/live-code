package adam.dev.liveCode.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name="room_user")
public class RoomUser {

   @EmbeddedId
   private RoomUserId id;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_active")
    private boolean isActive;

    public User getUser() {
     return user;
    }

    public void setUser(User user) {
     this.user = user;
    }

 public void setRoom(Room room) {
  this.room = room;
 }

 public void setRole(Role role) {
  this.role = role;
 }

 public RoomUserId getId() {
  return id;
 }

 public void setId(RoomUserId id) {
  this.id = id;
 }

 public Room getRoom() {
  return room;
 }

 public Role getRole() {
  return role;
 }

 public boolean isActive() {
  return isActive;
 }

 public void setActive(boolean active) {
  isActive = active;
 }
}

