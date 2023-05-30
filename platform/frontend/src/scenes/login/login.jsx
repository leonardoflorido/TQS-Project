import * as React from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";

function Copyright(props) {
	function handleClick(e) {
		e.preventDefault();
		if (window.location.pathname === "/admin/login") {
			window.location.href = "/login";
		}
	}

	return (
		<Typography
			variant="body2"
			color="text.secondary"
			align="center"
			{...props}
		>
			{window.location.pathname === "/admin/login" ? (
				<Link href="/admin/login" onClick={handleClick}>
					Pickup
				</Link>
			) : (
				<Link href="/admin/login">
					Admin?
				</Link>
			)}
			<div style={{ margin: '40px' }}></div>
			{"Copyright © "}
			<Link color="inherit" href="#">
				TQS
			</Link>{" "}
			{new Date().getFullYear()}
			{"."}
		</Typography>
	);
}

const theme = createTheme();

export default function SignIn(props) {
	const handleSubmit = async (event) => {
		event.preventDefault();
		const credentials = new FormData(event.currentTarget);

		if (props.user === "admin") {
			const response = await fetch("http://localhost:8080/admin/login", {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify({
					email: credentials.get("email"),
					password: credentials.get("password"),
				}),
			});
			

			if (response.status === 200) {
				// set local storage token
				const data = await response.json();
				localStorage.setItem("user", data.name);

				window.location.href = "/admin/dashboard";
			} else {
				alert("Login failed");
			}
		} else {
			const response = await fetch("http://localhost:8080/pickup/login", {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify({
					email: credentials.get("email"),
					password: credentials.get("password"),
				}),
			});

			if (response.status === 200) {
				const data = await response.json();
				localStorage.setItem("pickupId", data.id);
				window.location.href = "/acp/dashboard";

			} else if (response.status === 401) {
				alert("Your account is still pending");
			} else {
				alert("Login failed - invalid credentials");
			}
		}
	};

	return (
		<ThemeProvider theme={theme}>
			<Container component="main" maxWidth="xs">
				<CssBaseline />
				<Box
					sx={{
						marginTop: 8,
						display: "flex",
						flexDirection: "column",
						alignItems: "center",
					}}
				>
					<Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
						<LockOutlinedIcon />
					</Avatar>
					<Typography component="h1" variant="h5">
						Sign in
					</Typography>
					<Box
						component="form"
						onSubmit={handleSubmit}
						noValidate
						sx={{ mt: 1 }}
					>
						<TextField
							margin="normal"
							required
							fullWidth
							id="email"
							label="Email Address"
							name="email"
							autoComplete="email"
							autoFocus
						/>
						<TextField
							margin="normal"
							required
							fullWidth
							name="password"
							label="Password"
							type="password"
							id="password"
							autoComplete="current-password"
						/>
						<Button
							type="submit"
							fullWidth
							variant="contained"
							sx={{ mt: 3, mb: 2 }}
						>
							Sign In
						</Button>
						<Grid container>
							<Grid item>
								<Link href="/signup" variant="body2">
									{"Don't have an account? Sign Up"}
								</Link>
							</Grid>
						</Grid>
					</Box>
				</Box>
				<Copyright sx={{ mt: 8, mb: 4 }} />
			</Container>
		</ThemeProvider>
	);
}
