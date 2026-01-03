export default function AdminUserItem({ user, onChangeRole }) {
  return (
    <li>
      {user.username} ({user.role})
      <select
        value={user.role}
        onChange={(e) => onChangeRole(user.id, e.target.value)}
      >
        <option value="USER">USER</option>
        <option value="ADMIN">ADMIN</option>
      </select>
    </li>
  );
}
