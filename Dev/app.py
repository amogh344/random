from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import requests
import subprocess

app = FastAPI()

# Replace with your Ollama API endpoint
OLLAMA_API_URL = "http://localhost:11434/mistral"  # Adjust the port if necessary

class QueryRequest(BaseModel):
    query: str

class TaskRequest(BaseModel):
    command: str

@app.post("/query")
def process_query(request: QueryRequest):
    """Process user queries using Mistral."""
    try:
        response = requests.post(OLLAMA_API_URL, json={"prompt": request.query})
        response.raise_for_status()  # Raise an error for bad responses
        return {"response": response.json().get("completion")}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

@app.post("/task")
def execute_task(request: TaskRequest):
    """Execute system commands."""
    try:
        result = subprocess.run(request.command, shell=True, capture_output=True, text=True)
        return {"output": result.stdout, "error": result.stderr}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

@app.get("/health")
def health_check():
    """Check if the server is running."""
    return {"status": "Jarvis is online!"}