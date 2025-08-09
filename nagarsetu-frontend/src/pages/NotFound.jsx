import { Link } from "react-router-dom";

export default function NotFound() {
  return (
    <div style={{ padding: "20px" }}>
      <h2>404 - Page Not Found</h2>
      <Link to="/">Go to Home</Link>
    </div>
  );
}
