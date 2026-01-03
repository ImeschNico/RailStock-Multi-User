import AdminUserItem from "./AdminUserItem";

export default function AdminUserList({ users, onChangeRole }) {
  return (
    <ul>
      {users.map((user) => (
        <AdminUserItem key={user.id} user={user} onChangeRole={onChangeRole} />
      ))}
    </ul>
  );
}
