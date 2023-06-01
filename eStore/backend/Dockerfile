FROM python:3.10.9-bullseye
ENV PYTHONUNBUFFERED 1
WORKDIR /backend
COPY . .
EXPOSE 8000
RUN pip install -r requirements.txt
CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]