import * as React from 'react';
import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';
import Title from '../../components/Title.tsx';

function preventDefault(event: React.MouseEvent) {
  event.preventDefault();
}

export default function Deposits() {
  return (
    <React.Fragment>
      <Title>Recent Orders</Title>
      <Typography component="p" variant="h4">
        7
      </Typography>
      <Typography color="text.secondary" sx={{ flex: 1 }}>
        on 12 May, 2023
      </Typography>
      <div>
        <Link color="primary" href="#" onClick={preventDefault}>
          View oders
        </Link>
      </div>
    </React.Fragment>
  );
}