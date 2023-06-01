FROM node:latest
COPY . ./
ENV GENERATE_SOURCEMAP=false
RUN npm install --force
ENV PORT=3001
EXPOSE $PORT
CMD ["npm", "start"]