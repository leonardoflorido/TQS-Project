FROM node:latest
COPY . ./
RUN npm install --force
ENV PORT=3001
EXPOSE $PORT
CMD ["npm", "start"]