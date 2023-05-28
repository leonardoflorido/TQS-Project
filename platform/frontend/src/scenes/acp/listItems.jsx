import * as React from "react";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import DashboardIcon from "@mui/icons-material/Dashboard";
import PersonIcon from "@mui/icons-material/Person";

export const mainListItems = (
	<React.Fragment>
		<ListItemButton>
			<ListItemIcon>
				<DashboardIcon />
			</ListItemIcon>
			<ListItemText primary="Dashboard" />
		</ListItemButton>
		<ListItemButton>
			<ListItemIcon>
				<PersonIcon />
			</ListItemIcon>
			<ListItemText primary="Logout" onClick={(e) => {e.preventDefault();localStorage.removeItem("pickupId"); window.location.href="/login" }}/>
		</ListItemButton>
	</React.Fragment>
);

