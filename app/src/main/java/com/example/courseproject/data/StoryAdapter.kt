package com.example.courseproject.data

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.courseproject.R
import com.example.courseproject.detective_story.DetectiveStoryActivity
import com.example.courseproject.fragments.MiddleDialogFragment
import com.example.courseproject.fragments.RestartFragment

class StoryAdapter(context: Context, private val stories: List<Story>)
    : RecyclerView.Adapter<StoryAdapter.ViewHolder> () {

    private val inflater : LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_story, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = stories.size

    private fun getItem(position: Int) : Story = stories[position]

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chapterNumber : TextView = itemView.findViewById(R.id.chapter_number)
        private val cover : ImageView = itemView.findViewById(R.id.cover)
        private val available : TextView = itemView.findViewById(R.id.available)
        private val startStory : ImageView = itemView.findViewById(R.id.play_container)
        private val resumeStory : ImageView = itemView.findViewById(R.id.resume_container)
        private val restartButton : ImageView = itemView.findViewById(R.id.refresh_container)

        fun bind(story : Story) {
            val sp : SharedPreferences = itemView.context.getSharedPreferences(story.storyName, Context.MODE_PRIVATE)
            val chapterNumberString = "chapter number"
            val startString = "started"

            if (!sp.getBoolean(startString, false)) {
                startStory.visibility = View.VISIBLE
                val playText : TextView = itemView.findViewById(R.id.play)
                playText.visibility = View.VISIBLE
                val one : TextView = itemView.findViewById(R.id.ticket_number)
                one.visibility = View.VISIBLE

                resumeStory.visibility = View.INVISIBLE
                val resumeText : TextView = itemView.findViewById(R.id.resume)
                resumeText.visibility = View.INVISIBLE
            }

            val chNumber = sp.getInt(chapterNumberString, 1)
            if(chNumber <= story.chaptersAvailable) {
                available.text = buildString {
                    append("AVAILABLE FOR PLAYING")
                }
                if (story.storyName == "detective story") {
                    startStory.setOnClickListener {
                        openDetectiveStoryActivity(itemView)
                    }
                    resumeStory.setOnClickListener {
                        openDetectiveStoryActivity(itemView)
                    }
                }
            }
            else {
                available.text = buildString {
                    append("STORY IN PROGRESS")
                }
                startStory.setOnClickListener {
                    openStoryIsInProgressFragment(itemView)
                }
            }
            restartButton.setOnClickListener {
                openRestartFragment(itemView, story.storyName)
            }
            cover.setImageResource(story.background)
            chapterNumber.text = buildString {
                append("CHAPTER ")
                append(chNumber)
            }
        }

        private fun openRestartFragment(view: View, storyName: String) {
            val restartFragment = RestartFragment(storyName)
            val activity : AppCompatActivity = view.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().add(R.id.activity_main_frame, restartFragment).commit()
        }

        private fun openStoryIsInProgressFragment(view: View) {
            val storyInProgressFragment = MiddleDialogFragment("New chapters of this\n story will be available\n soon!\"")
            val activity : AppCompatActivity = view.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().add(R.id.activity_main_frame, storyInProgressFragment).commit()
        }

        private fun openDetectiveStoryActivity(view: View) {
            val intent = Intent(view.context, DetectiveStoryActivity::class.java)
            view.context.startActivity(intent)
        }
    }

}