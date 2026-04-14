from fastapi import FastAPI

from app.api.search import router, init_service
from app.ml.embedder import model_embed
from app.ml.load_model import load_model
from app.api.SearchService import SearchService

app = FastAPI()

model = load_model("app/search_model.pkl")

service = SearchService(
        embedder=model_embed,
        model=model
)

init_service(service)

app.include_router(router)
