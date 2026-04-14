import numpy as np
from sklearn.metrics.pairwise import cosine_similarity

class SearchService:
    def __init__(self, embedder, model):
        self.embedder = embedder
        self.model = model

    def search(self, text: str, top_n: int = 3):
        query_vec = self.embedder.encode([text])

        sims = cosine_similarity(
                query_vec,
                self.model["embeddings"]
            )[0]

        top_idx = np.argsort(sims)[::-1][:top_n]

        return [
            {
                "name": self.model["objects"][i]["name"],
                "score": float(sims[i])
            }
            for i in top_idx
        ]
