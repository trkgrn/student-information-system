import {Role} from "./Role";

export class User {
  userId?: number;
  tckNo?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  telNo?: string;
  address?: string;
  password?: string;
  role?: Role;
}
