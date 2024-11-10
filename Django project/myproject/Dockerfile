# Use an official Python runtime as a parent image
FROM python:slim

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Install any needed dependencies specified in requirements.txt
RUN pip install --no-cache-dir -r "Django project/myproject/requirements.txt"

# Run app.py when the container launches
CMD ["python", "manage.py" , "runserver", "0.0.0.0:8000"]

EXPOSE 8000
