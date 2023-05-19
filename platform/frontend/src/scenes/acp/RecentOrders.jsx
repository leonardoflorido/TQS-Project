import * as React from 'react';
import Typography from '@mui/material/Typography';
import Title from '../../components/Title';

export default function Deposits() {
  const [data, setData] = React.useState({});
  React.useEffect(() => {
    // Fetch pickup orders from api
    // Filter the most recent date
    // Sum the total of orders
    // setData({date, totalOrders})
  }, []);
  return (
    <React.Fragment>
      <Title>Recent Orders</Title>
      <Typography component="p" variant="h4">
        7
      </Typography>
      <Typography color="text.secondary" sx={{ flex: 1 }}>
        on 12 May, 2023
      </Typography>
    </React.Fragment>
  );
}