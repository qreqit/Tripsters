import { Trip } from "./Trip"

export type User = {
  name: string,
  dateOfBirth: string,
  id: number,
  profilePic: string,
  trips: Trip[],
  friends: User[],
}