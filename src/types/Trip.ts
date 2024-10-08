import { User } from "./User";

export type Trip = {
  name: string;
  startPoint: string;
  finishPoint: string | undefined;
  startDate: string;
  finishDate: string;
  aditionalPoints: string[];
  members: User[];
  link: string;
  owner: User;
  status: 'incoming' | 'in progress' | 'completed';
}